import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vista9',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './vista9.component.html',
  styleUrls: ['./vista9.component.scss']
})
export class Vista9Component {

  private router = inject(Router);

  receta: any = null;

  constructor() {
    const nav = this.router.getCurrentNavigation();
    this.receta = nav?.extras.state?.['receta'] || null;
  }

  volver() {
    this.router.navigate(['/vista4']);
  }
  editarReceta() {
    this.router.navigate(['/vista10'], {
      state: { receta: this.receta }
    });
  }
  anadirCompra() {
    // Aquí se añadirá la lógica de añadir a la compra
    console.log('Añadir receta a la compra');
  }


  irDescubrir() { this.router.navigate(['/vista4']); }
  irFavoritos() { this.router.navigate(['/vista5']); }
  irCompras() { this.router.navigate(['/vista6']); }
  irPlanificar() { this.router.navigate(['/vista8']); }
}
