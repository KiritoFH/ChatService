import { Component, ChangeDetectionStrategy, ViewChild, TemplateRef, ViewEncapsulation, OnInit } from '@angular/core';
import { 
  startOfDay, subDays, addDays, endOfMonth, isSameDay, isSameMonth, addHours
} from 'date-fns';
import { EventColor } from 'calendar-utils';
import { Subject } from 'rxjs';
import { CalendarEvent, CalendarEventAction,  CalendarView } from 'angular-calendar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-calendar-month',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './calendar-month.component.html',
  styleUrls: ['../calendar.css'],
  encapsulation: ViewEncapsulation.None
})

export class CalendarMonthComponent implements OnInit {
  @ViewChild('modalContent', { static: true }) modalContent!: TemplateRef<any>;

  view: CalendarView = CalendarView.Month;
  CalendarView = CalendarView;
  viewDate: Date = new Date();
  refresh = new Subject<void>();
  events: CalendarEvent[] = [];
  activeDayIsOpen: boolean = true;
  x: { r: number, g: number, b: number, a: number } = { r: 200, g: 100, b: 50, a: 1 };
  constructor(private router: Router) {}

  ngOnInit() {
    this.events = [
      {
        start: subDays(startOfDay(new Date()), 1),
        end: new Date(),
        title: `Plumbing (${subDays(startOfDay(new Date()), 1).toLocaleString([], {hour: '2-digit', minute:'2-digit'})} to ${new Date().toLocaleString([], {hour: '2-digit', minute:'2-digit'})})`,
        color: this.getRandomColour(),
        allDay: true
      },
      {
        start: startOfDay(new Date()),
        end: addDays(new Date(), 1),
        title: `Moving (${startOfDay(new Date()).toLocaleString([], {hour: '2-digit', minute:'2-digit'})} to ${addDays(new Date(), 1).toLocaleString([], {hour: '2-digit', minute:'2-digit'})})`,
        color: this.getRandomColour()
      },
      {
        start: subDays(endOfMonth(new Date()), 3),
        end: addDays(endOfMonth(new Date()), 3),
        title: 'N.A.',
        cssClass: 'N.A.',
        color: this.getRandomColour(),
        allDay: true,
      },
      {
        start: addHours(startOfDay(new Date()), 2),
        end: addHours(new Date(), 2),
        title: `Electrical (${addHours(startOfDay(new Date()), 2).toLocaleString([], {hour: '2-digit', minute:'2-digit'})} to ${addHours(new Date(), 2).toLocaleString([], {hour: '2-digit', minute:'2-digit'})})`,
        color: this.getRandomColour()
      },
    ];
  }

  getRandomColour(): EventColor {
    const o = Math.round, r = Math.random, s = 255;
    const rgba = { r: o(r()*s), g: o(r()*s), b: o(r()*s), a: 1 };
    return { primary: this.rgbaToString(rgba), secondary: this.rgbaLighterToString(rgba) };
  }
  
  rgbaLighterToString(x: { r: number, g: number, b: number, a: number }): string {
    return this.rgbaToString({ r: x.r, g: x.g, b: x.b, a: 0.1 });
  }

  rgbaToString(x: { r: number, g: number, b: number, a: number }): string {
    return `rgba(${x.r}, ${x.g}, ${x.b}, ${x.a})`;
  }

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    }
  }

  navigateToChat(event: CalendarEvent): void {
    const compare = event.cssClass ?? 'valid';
    if (compare !== 'N.A.') {
      this.router.navigate(['chat']);
    }
  }

  setView(view: CalendarView): void {
    this.view = view;
  }

  closeOpenMonthViewDay(): void {
    this.activeDayIsOpen = false;
  }
}
