import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-vista2',
  templateUrl: './vista2.component.html',
  styleUrls: ['./vista2.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class Vista2Component {
  private router = inject(Router);
  usuario: string = '';
  contrasena: string = '';
  recordarme: boolean = false;

  volver() {
    this.router.navigate(['/']);
  }

  iniciarSesion() {
    console.log('Iniciar sesión:', { usuario: this.usuario, contrasena: this.contrasena, recordarme: this.recordarme });this.router.navigate(['/vista4']);
  }
}
