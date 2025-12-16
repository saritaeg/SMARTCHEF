import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

import { RecetaService } from '../servicio/receta-service';
import { RecetaDetalle } from '../modelos/receta-detalle.model';

@Component({
  selector: 'app-vista9',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './vista9.component.html',
  styleUrls: ['./vista9.component.scss']
})
export class Vista9Component implements OnInit {

  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private recetaService = inject(RecetaService);

  receta: RecetaDetalle | null = null;
  cargando = true;

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.cargarReceta(id);
  }

  cargarReceta(id: number): void {
    this.recetaService.obtenerDetalleReceta(id).subscribe({
      next: data => {
        this.receta = data;
        this.cargando = false;
      },
      error: err => {
        console.error('Error al cargar receta', err);
        this.cargando = false;
      }
    });
  }

  volver() {
    this.router.navigate(['/vista4']);
  }

  editarReceta() {
    this.router.navigate(['/vista10', this.receta?.idReceta]);
  }

  anadirCompra() {
    console.log('Añadir receta a la compra');
  }

  irDescubrir() { this.router.navigate(['/vista4']); }
  irFavoritos() { this.router.navigate(['/vista5']); }
  irCompras() { this.router.navigate(['/vista6']); }
  irPlanificar() { this.router.navigate(['/vista8']); }
}
