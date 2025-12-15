import { Routes } from '@angular/router';
import { Vista1Component } from './vista1/vista1.component';
import { Vista2Component } from './vista2/vista2.component';
import { Vista3Component } from './vista3/vista3.component';
import { Vista4Component } from './vista4/vista4.component';
import { Vista5Component } from './vista5/vista5.component';
import { Vista6Component } from './vista6/vista6.component';
import { Vista8Component } from './vista8/vista8.component';
import { Vista9Component } from './vista9/vista9.component';
import { Vista10Component } from './vista10/vista10.component';
import { Vista11Component } from './vista11/vista11.component';

export const routes: Routes = [
  { path: '', component: Vista1Component },
  { path: 'vista2', component: Vista2Component },
  { path: 'vista3', component: Vista3Component },
  { path: 'vista4', component: Vista4Component },
  { path: 'vista5', component: Vista5Component },
  { path: 'vista6', component: Vista6Component },
  { path: 'vista8', component: Vista8Component },
  { path: 'vista9', component: Vista9Component },
  { path: 'vista10', component: Vista10Component },
  { path: 'vista11', component: Vista11Component },
  { path: '**', redirectTo: '' }
];
