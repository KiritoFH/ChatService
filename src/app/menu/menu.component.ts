import { Component } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CalendarWeekComponent } from '../calendar/calendar-week/calendar-week.component';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {
  constructor(private modalService: NgbModal) {}

  checkAvailability() {
		this.modalService.open(CalendarWeekComponent, { size: 'xl', centered: true });
	}
}
