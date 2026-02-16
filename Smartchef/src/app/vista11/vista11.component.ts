import { Component, inject, ViewChild, ElementRef } from '@angular/core'; // Añadido ViewChild, ElementRef
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http'; // Importante para subir foto
import { RecetaService } from '../servicio/receta-service';

@Component({
  selector: 'app-vista11',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './vista11.component.html',
  styleUrls: ['./vista11.component.scss']
})
export class Vista11Component {

  private router = inject(Router);
  private recetaService = inject(RecetaService);
  private http = inject(HttpClient); // Inyectamos HttpClient directamente

  // Variables para la cámara
  mostrarCamara = false;
  stream: MediaStream | null = null;

  @ViewChild('videoElement') videoElement!: ElementRef<HTMLVideoElement>;
  @ViewChild('canvasElement') canvasElement!: ElementRef<HTMLCanvasElement>;

  receta = {
    titulo: '',
    img: '', // Aquí se guardará la URL que nos devuelva el backend
    descripcion: '',
    tiempo: '',
    porciones: 1,
    ingredientes: '',
    pasos: ''
  };

  // --- LÓGICA DE CÁMARA ---

  async iniciarCamara() {
    this.mostrarCamara = true;
    try {
      this.stream = await navigator.mediaDevices.getUserMedia({ video: true });
      // Damos un pequeño delay para que el elemento video se renderice en el DOM
      setTimeout(() => {
        if (this.videoElement) {
          this.videoElement.nativeElement.srcObject = this.stream;
        }
      }, 100);
    } catch (err) {
      console.error('Error al acceder a la cámara:', err);
      alert('No se pudo acceder a la cámara.');
      this.mostrarCamara = false;
    }
  }

  tomarFoto() {
    if (!this.videoElement || !this.canvasElement) return;

    const video = this.videoElement.nativeElement;
    const canvas = this.canvasElement.nativeElement;

    // Ajustar tamaño del canvas al video
    canvas.width = video.videoWidth;
    canvas.height = video.videoHeight;

    // Dibujar el frame actual en el canvas
    const context = canvas.getContext('2d');
    context?.drawImage(video, 0, 0, canvas.width, canvas.height);

    // Convertir a Blob y subir
    canvas.toBlob(blob => {
      if (blob) {
        this.subirFotoAlBackend(blob);
      }
    }, 'image/jpeg');

    this.detenerCamara();
  }

  detenerCamara() {
    if (this.stream) {
      this.stream.getTracks().forEach(track => track.stop());
    }
    this.mostrarCamara = false;
  }

  subirFotoAlBackend(blob: Blob) {
    const formData = new FormData();
    formData.append('file', blob, 'foto_camara.jpg');

    // Subir al endpoint que creamos en Spring Boot
    this.http.post<any>('http://localhost:8080/api/media/upload', formData).subscribe({
      next: (resp) => {
        this.receta.img = resp.url; // Asignamos la URL recibida al campo de la receta
        alert('¡Foto subida correctamente!');
      },
      error: (err) => {
        console.error('Error al subir foto', err);
        alert('Error al subir la foto.');
      }
    });
  }

  // --- FIN LÓGICA CÁMARA ---

  volver() {
    this.detenerCamara(); // Asegurar que se apaga si volvemos
    this.router.navigate(['/vista4']);
  }

  confirmar() {
    const dto = {
      idUsuario: 1, // Ojo: esto debería venir de un auth service real
      nombre: this.receta.titulo,
      descripcion: this.receta.descripcion,
      tiempoPreparacion: Number(this.receta.tiempo) || 0, // Evitar NaN
      vegetariano: false,
      sinGluten: false,
      rapido: false,
      economico: false,
      fotoUrl: this.receta.img,

      ingredientes: this.parseIngredientes(this.receta.ingredientes),
      instrucciones: this.parsePasos(this.receta.pasos)
    };

    this.recetaService.crearReceta(dto).subscribe({
      next: () => {
        console.log('Receta creada correctamente');
        this.volver();
      },
      error: err => {
        console.error('Error al crear receta', err);
      }
    });
  }

  parseIngredientes(texto: string) {
    if (!texto) return [];
    return texto.split(',').map((nombre: string) => ({
      nombre: nombre.trim(),
      cantidad: 1,
      unidad: 'ud',
      categoria: 'OTRO'
    }));
  }

  parsePasos(texto: string) {
    if (!texto) return [];
    return texto.split(',').map((desc: string, index: number) => ({
      pasoNumero: index + 1,
      descripcion: desc.trim(),
      imagenUrl: ''
    }));
  }
}
