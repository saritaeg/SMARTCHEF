import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { HttpClientModule } from '@angular/common/http';
import { UsuarioService } from '../servicio/usuario-service';

@Component({
  selector: 'app-vista2',
  templateUrl: './vista2.component.html',
  styleUrls: ['./vista2.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, IonicModule, HttpClientModule]
})
export class Vista2Component {
  private router = inject(Router);
  private usuarioService = inject(UsuarioService);

  usuario: string = '';
  contrasena: string = '';

  recordarme: boolean = false;

  volver() {
    this.router.navigate(['/']);
  }

  iniciarSesion() {
    const email = this.usuario.trim().toLowerCase();
    const contrasena = this.contrasena.trim();

    if (!email || !contrasena) {
      alert('Debe completar email y contraseña');
      return;
    }

    this.usuarioService.login(email, contrasena).subscribe({
      next: (res) => {
        console.log('Login OK, respuesta backend:', res);
        alert('Login correcto');
        this.router.navigate(['/vista4']);
      },
      error: (err) => {
        console.error('Login fallido, error backend:', err);
        if (err.status === 401) {
          alert('Correo o contraseña incorrectos');
        } else if (err.error) {
          alert('Error exacto del backend: ' + JSON.stringify(err.error));
        } else {
          alert('Error desconocido: ' + JSON.stringify(err));
        }
      }
    });
  }

}
