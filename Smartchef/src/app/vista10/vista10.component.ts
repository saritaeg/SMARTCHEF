import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RecetaService } from '../servicio/receta-service';
import {IngredienteCantidad, Instruccion, RecetaDetalle} from "../modelos/receta-detalle.model";

@Component({
  selector: 'app-vista10',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './vista10.component.html',
  styleUrls: ['./vista10.component.scss']
})
export class Vista10Component implements OnInit {

  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private recetaService = inject(RecetaService);

  idReceta!: number;
  titulo = '';
  descripcion = '';
  ingredientes: IngredienteCantidad[] = [];
  pasos: Instruccion[] = [];
  fotoUrl = '';

  ngOnInit(): void {
    this.idReceta = Number(this.route.snapshot.paramMap.get('id'));
    this.cargarReceta();
  }

  cargarReceta() {
    this.recetaService.obtenerDetalleReceta(this.idReceta).subscribe({
      next: (receta: RecetaDetalle) => {
        this.titulo = receta.nombre;
        this.descripcion = receta.descripcion;
        this.ingredientes = [...receta.ingredientes];
        this.pasos = [...receta.instrucciones];
        this.fotoUrl = receta.fotoUrl;
      },
      error: err => console.error('Error al cargar receta', err)
    });
  }

  anadirIngrediente() {
    this.ingredientes.push({ nombre: '', cantidad: 0, unidad: '', categoria: '' });
  }

  eliminarIngrediente(i: number) {
    this.ingredientes.splice(i, 1);
  }

  anadirPaso() {
    this.pasos.push({ pasoNumero: this.pasos.length + 1, descripcion: '', imagenUrl: '' });
  }

  eliminarPaso(i: number) {
    this.pasos.splice(i, 1);
  }

  confirmar() {
    const dto = {
      nombre: this.titulo,
      descripcion: this.descripcion,
      tiempoPreparacion: 0,
      vegetariano: false,
      sinGluten: false,
      rapido: false,
      economico: false,
      fotoUrl: this.fotoUrl,
      idUsuario: 1,
      ingredientes: this.ingredientes,
      instrucciones: this.pasos
    };

    this.recetaService.actualizarReceta(this.idReceta, dto).subscribe({
      next: () => this.router.navigate(['/vista9', this.idReceta]),
      error: err => console.error('Error al actualizar receta', err)
    });
  }

  volver() {
    this.router.navigate(['/vista9', this.idReceta]);
  }
}
