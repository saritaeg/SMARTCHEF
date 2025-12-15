import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-vista5',
  templateUrl: './vista5.component.html',
  styleUrls: ['./vista5.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class Vista5Component implements OnInit {
  private router = inject(Router);

  coleccionNombre: string = '';
  coleccionExpandida: boolean = false;

  // Cards
  cards = [
    { titulo: "Pechitos de pollo con verduras", img: "assets/pinchito.jpeg", meta: "25 min • 1 persona", categoria: "Todas" },
    { titulo: "Wrap de Pollo y Aguacate", img: "assets/wrap.jpeg", meta: "15 min • 2 personas", categoria: "Todas" },
    { titulo: "Pasta Integral con Pesto", img: "assets/pasta.jpeg", meta: "30 min • 3 personas", categoria: "Postres" }
  ];

  cardsFiltradas = [...this.cards]; // inicial: todas

  // Tabs
  tabs = [
    { nombre: 'Todas', count: 3, activo: true },
    { nombre: 'Postres', count: 1, activo: false }
  ];

  ngOnInit() {
    // Inicializar cards filtradas
    this.cardsFiltradas = [...this.cards];
  }

  // Cambiar tab y filtrar cards
  cambiarTab(tab: any) {
    this.tabs.forEach(t => t.activo = false);
    tab.activo = true;

    if (tab.nombre === 'Todas') {
      this.cardsFiltradas = [...this.cards];
    } else {
      this.cardsFiltradas = this.cards.filter(c => c.categoria === tab.nombre);
    }
  }

  // Abrir receta
  abrirReceta(card: any) {
    this.router.navigate(['/vista9'], { state: { receta: card } });
  }

  // Crear colección expandida
  abrirColeccion() {
    this.coleccionExpandida = true;
  }

  crearColeccion() {
    if (!this.coleccionNombre.trim()) return;
    console.log('Crear colección:', this.coleccionNombre);
    this.coleccionNombre = '';
    this.coleccionExpandida = false;
  }

  cancelarColeccion() {
    this.coleccionNombre = '';
    this.coleccionExpandida = false;
  }

  // Navegación inferior
  irDescubrir() { this.router.navigate(['/vista4']); }
  irFavoritos() { this.router.navigate(['/vista5']); }
  irCompras() { this.router.navigate(['/vista6']); }
  irPlanificar() { this.router.navigate(['/vista8']); }
}
