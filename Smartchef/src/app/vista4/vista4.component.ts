import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-vista4',
  templateUrl: './vista4.component.html',
  styleUrls: ['./vista4.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class Vista4Component {

  private router = inject(Router);
  searchQuery: string = '';

  // --------------------------------
  // LISTA DE CARDS
  // --------------------------------
  cards = [
    {
      titulo: "Pechitos de pollo con verduras",
      img: "assets/pinchito.jpeg",
      meta: "25 min • 1 persona",
      favorito: false
    },
    {
      titulo: "Wrap de Pollo, Aguacate y Queso",
      img: "assets/wrap.jpeg",
      meta: "15 min • 2 personas",
      favorito: false
    },
    {
      titulo: "Pasta Integral con Pesto",
      img: "assets/pasta.jpeg",
      meta: "30 min • 3 personas",
      favorito: false
    }

  ];

  // --------------------------------
  // FILTROS
  // --------------------------------
  desplegarFiltros: boolean = false;

  filtros = {
    vegetariano: false,
    sinGluten: false,
    rapido: false,
    economico: false
  };


  textoFiltros: string = "Filtros";

  toggleDesplegable() {
    this.desplegarFiltros = !this.desplegarFiltros;
  }

  cerrarFiltros() {
    this.desplegarFiltros = false;
  }

  aplicarFiltros() {
    const seleccionados: string[] = [];

    if (this.filtros.vegetariano) seleccionados.push("Vegetariano");
    if (this.filtros.sinGluten) seleccionados.push("Sin gluten");
    if (this.filtros.rapido) seleccionados.push("Rápido");
    if (this.filtros.economico) seleccionados.push("Económico");

    this.textoFiltros = seleccionados.length > 0
      ? seleccionados.join(", ")
      : "Filtros";

    this.desplegarFiltros = false;
  }

  // --------------------------------
  // FUNCIONES CARDS
  // --------------------------------
  toggleFavorito(card: any) {
    card.favorito = !card.favorito;
  }

  buscar() {
    console.log('Buscando:', this.searchQuery);
  }

  // --------------------------------
  // NAVEGACIÓN
  // --------------------------------
  irDescubrir() {
    this.router.navigate(['/vista4']);
  }

  irFavoritos() {
    this.router.navigate(['/vista5']);
  }

  irCompras() {
    console.log('/vista6');
  }



  irPlanificar() {
    console.log('/vista8');
  }

  // Reemplaza la función abrirReceta para que solo el botón recete la abra
  abrirReceta(card: any) {
    this.router.navigate(['/vista9'], {
      state: { receta: card }
    });
  }
  // Función para eliminar la card de la lista
  eliminarCard(index: number) {
    this.cards.splice(index, 1);
  }

// Nueva función para el botón +
  anadirReceta() {
    this.router.navigate(['/vista11']);
  }


}
