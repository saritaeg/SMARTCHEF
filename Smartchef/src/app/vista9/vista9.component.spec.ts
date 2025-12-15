import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Vista9Component } from './vista9.component';

describe('Vista9Component', () => {
  let component: Vista9Component;
  let fixture: ComponentFixture<Vista9Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Vista9Component]
    }).compileComponents();

    fixture = TestBed.createComponent(Vista9Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
