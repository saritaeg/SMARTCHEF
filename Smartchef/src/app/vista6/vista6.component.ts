import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-vista6',
  templateUrl: './vista6.component.html',
  styleUrls: ['./vista6.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class Vista6Component {

  private router = inject(Router);

  ingredientes: { nombre: string; cantidad: string; categoria?: string }[] = [
    { nombre: 'Chocolate', cantidad: '1 unidad' },
    { nombre: 'Croquetas', cantidad: '200 g' },
    { nombre: 'Helado', cantidad: '500 ml' }
  ];

  formularioVisible: boolean = false;

  nuevoIngrediente = { nombre: '', cantidad: '', categoria: '' };

  // Mostrar formulario
  mostrarFormulario() {
    this.formularioVisible = true;
  }

  // Cerrar formulario
  cerrarFormulario() {
    this.formularioVisible = false;
    this.nuevoIngrediente = { nombre: '', cantidad: '', categoria: '' };
  }

  // Añadir ingrediente
  agregarIngrediente() {
    if (!this.nuevoIngrediente.nombre.trim() || !this.nuevoIngrediente.cantidad.trim()) return;
    this.ingredientes.push({ ...this.nuevoIngrediente });
    this.cerrarFormulario();
  }

  // Marcar comprado
  marcarComprado(index: number) {
    this.ingredientes.splice(index, 1);
  }

  // Navegación
  irDescubrir() { this.router.navigate(['/vista4']); }
  irFavoritos() { this.router.navigate(['/vista5']); }
  irCompras() { this.router.navigate(['/vista6']); }
  irPlanificar() { this.router.navigate(['/vista8']); }
}
