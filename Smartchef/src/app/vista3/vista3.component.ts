import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-vista3',
  templateUrl: './vista3.component.html',
  styleUrls: ['./vista3.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class Vista3Component {
  private router = inject(Router);
  usuario: string = '';
  correo: string = '';
  contrasena: string = '';
  confirmarContrasena: string = '';
  recordarme: boolean = false;

  volver() {
    this.router.navigate(['/']);
  }

  registrarse() {
    console.log('Registrarse:', {
      usuario: this.usuario,
      correo: this.correo,
      contrasena: this.contrasena,
      confirmarContrasena: this.confirmarContrasena,
      recordarme: this.recordarme
    });
    this.router.navigate(['/vista4']);
  }

}
