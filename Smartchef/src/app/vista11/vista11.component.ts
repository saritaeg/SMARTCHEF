import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
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

  receta = {
    titulo: '',
    img: '',
    descripcion: '',
    tiempo: '',
    porciones: 1,
    ingredientes: '',
    pasos: ''
  };

  volver() {
    this.router.navigate(['/vista4']);
  }

  confirmar() {
    const dto = {
      idUsuario: 1,
      nombre: this.receta.titulo,
      descripcion: this.receta.descripcion,
      tiempoPreparacion: Number(this.receta.tiempo),
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
    return texto.split(',').map((nombre: string) => ({
      nombre: nombre.trim(),
      cantidad: 1,
      unidad: 'ud',
      categoria: 'OTRO'
    }));
  }

  parsePasos(texto: string) {
    return texto.split(',').map((desc: string, index: number) => ({
      pasoNumero: index + 1,
      descripcion: desc.trim(),
      imagenUrl: ''
    }));
  }
}
