import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeasingEditComponent } from './leasing-edit.component';

describe('LeasingEditComponent', () => {
  let component: LeasingEditComponent;
  let fixture: ComponentFixture<LeasingEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LeasingEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LeasingEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
