import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vista1',
  templateUrl: './vista1.component.html',
  styleUrls: ['./vista1.component.scss'],
  standalone: true
})
export class Vista1Component {
  private router = inject(Router);

  navegarALogin() {
    this.router.navigate(['/vista2']);
  }

  navegarARegistro() {
    this.router.navigate(['/vista3']);
  }
}
