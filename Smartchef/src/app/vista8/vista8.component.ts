import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-vista8',
  templateUrl: './vista8.component.html',
  styleUrls: ['./vista8.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class Vista8Component {

  private router = inject(Router);

  mostrarFormulario: boolean = false;
  fechaActual: Date = new Date();

  recetas: string[] = [
    'Pechitos de pollo con verduras',
    'Wrap de Pollo, Aguacate y Queso',
    'Pasta Integral con Pesto'
  ];

  form = {
    fecha: '',
    tipo: '',
    receta: '',
    porciones: 1
  };

  abrirFormulario() {
    this.mostrarFormulario = true;
  }

  cerrarFormulario() {
    this.mostrarFormulario = false;
  }

  cambiarFecha(dias: number) {
    const fecha = new Date(this.fechaActual);
    fecha.setDate(fecha.getDate() + dias);
    this.fechaActual = fecha;
  }

  irDescubrir() { this.router.navigate(['/vista4']); }
  irFavoritos() { this.router.navigate(['/vista5']); }
  irCompras() { this.router.navigate(['/vista6']); }
  irPlanificar() { this.router.navigate(['/vista8']); }
}
