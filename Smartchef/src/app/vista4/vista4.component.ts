import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { RecetaCard } from '../modelos/receta-card.model';
import { RecetaService } from '../servicio/receta-service';

@Component({
  selector: 'app-vista4',
  templateUrl: './vista4.component.html',
  styleUrls: ['./vista4.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class Vista4Component implements OnInit {

  private router = inject(Router);
  private recetaService = inject(RecetaService);

  searchQuery: string = '';
  cards: RecetaCard[] = [];
  usuarioId = 1;

  desplegarFiltros: boolean = false;
  filtros = {
    vegetariano: false,
    sinGluten: false,
    rapido: false,
    economico: false
  };
  textoFiltros: string = 'Filtros';

  ngOnInit(): void {
    this.cargarRecetas();
  }

  cargarRecetas(): void {
    this.recetaService.obtenerRecetas(
      this.searchQuery || undefined,
      this.filtros.vegetariano,
      this.filtros.sinGluten,
      this.filtros.rapido,
      this.filtros.economico
    ).subscribe({
      next: data => {
        this.cards = data.map(r => ({
          ...r,
          fotoUrl: r.fotoUrl || 'assets/default-recipe.jpeg',
          favorito: false
        }));
      },
      error: err => console.error('Error al cargar recetas:', err)
    });
  }

  toggleDesplegable(): void {
    this.desplegarFiltros = !this.desplegarFiltros;
  }

  cerrarFiltros(): void {
    this.desplegarFiltros = false;
  }

  aplicarFiltros(): void {
    const seleccionados: string[] = [];

    if (this.filtros.vegetariano) seleccionados.push('Vegetariano');
    if (this.filtros.sinGluten) seleccionados.push('Sin gluten');
    if (this.filtros.rapido) seleccionados.push('Rápido');
    if (this.filtros.economico) seleccionados.push('Económico');

    this.textoFiltros = seleccionados.length
      ? seleccionados.join(', ')
      : 'Filtros';

    this.desplegarFiltros = false;
    this.cargarRecetas();
  }

  buscar(): void {
    this.cargarRecetas();
  }

  toggleFavorito(card: RecetaCard): void {
    card.favorito = !card.favorito;

    this.recetaService.marcarFavorito(card.idReceta, this.usuarioId)
      .subscribe({
        next: () =>
          console.log(`Receta ${card.idReceta} marcada como favorita`),
        error: err => console.error(err)
      });
  }

  abrirReceta(card: RecetaCard): void {
    this.router.navigate(['/vista9', card.idReceta]);
  }


  eliminarCard(card: RecetaCard, index: number): void {
    this.recetaService.eliminarReceta(card.idReceta).subscribe({
      next: () => {
        this.cards.splice(index, 1);
        console.log('Receta eliminada correctamente');
      },
      error: err => {
        console.error('Error al eliminar receta', err);
      }
    });
  }


  anadirReceta(): void {
    this.router.navigate(['/vista11']);
  }

  irDescubrir(): void {
    this.router.navigate(['/vista4']);
  }

  irFavoritos(): void {
    this.router.navigate(['/vista5']);
  }

  irCompras(): void {
    console.log('/vista6');
  }

  irPlanificar(): void {
    console.log('/vista8');
  }
}
