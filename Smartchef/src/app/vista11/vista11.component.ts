import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-vista11',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './vista11.component.html',
  styleUrls: ['./vista11.component.scss']
})
export class Vista11Component {
  private router = inject(Router);

  receta: any = {
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
    console.log('Receta añadida (simulada):', this.receta);
    this.volver(); // Vuelve a la vista4, aún sin backend
  }
}
