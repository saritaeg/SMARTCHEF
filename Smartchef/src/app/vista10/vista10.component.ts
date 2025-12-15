import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vista10',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './vista10.component.html',
  styleUrls: ['./vista10.component.scss']
})
export class Vista10Component {
  private router = inject(Router);

  // Campos vacíos para rellenar
  titulo: string = '';
  descripcion: string = '';
  ingredientes: string[] = ['', '', '']; // tres campos vacíos por defecto
  pasos: string[] = ['', '', '']; // tres pasos vacíos por defecto

  volver() {
    this.router.navigate(['/vista9']);
  }

  confirmar() {
    console.log('Datos confirmados:', {
      titulo: this.titulo,
      descripcion: this.descripcion,
      ingredientes: this.ingredientes,
      pasos: this.pasos
    });
    // Aquí podrías guardar los datos o navegar
  }

  anadirIngrediente() {
    this.ingredientes.push('');
  }

  eliminarIngrediente(i: number) {
    this.ingredientes.splice(i, 1);
  }

  anadirPaso() {
    this.pasos.push('');
  }

  eliminarPaso(i: number) {
    this.pasos.splice(i, 1);
  }
}
