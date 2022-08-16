import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeasingListComponent } from './leasing-list.component';

describe('LeasingListComponent', () => {
  let component: LeasingListComponent;
  let fixture: ComponentFixture<LeasingListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LeasingListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LeasingListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
