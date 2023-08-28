import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  Injectable,
  ViewEncapsulation,
} from '@angular/core';
import { CalendarEvent, CalendarEventTitleFormatter } from 'angular-calendar';
import { WeekViewHourSegment } from 'calendar-utils';
import { fromEvent } from 'rxjs';
import { finalize, takeUntil } from 'rxjs/operators';
import { addDays, addMinutes, endOfWeek } from 'date-fns';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

function floorToNearest(amount: number, precision: number) {
  return Math.floor(amount / precision) * precision;
}

function ceilToNearest(amount: number, precision: number) {
  return Math.ceil(amount / precision) * precision;
}

@Injectable()
export class CustomEventTitleFormatter extends CalendarEventTitleFormatter {
}

@Component({
  selector: 'app-calendar-week',
  templateUrl: './calendar-week.component.html',
  styleUrls: ['../calendar.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [
    {
      provide: CalendarEventTitleFormatter,
      useClass: CustomEventTitleFormatter,
    },
  ],
  encapsulation: ViewEncapsulation.None
})
export class CalendarWeekComponent {
  viewDate = new Date();

  events: CalendarEvent[] = [];

  dragToCreateActive = false;

  weekStartsOn: 0 = 0;

  constructor(private cdr: ChangeDetectorRef, public activeModal: NgbActiveModal) {}

  startDragToCreate(
    segment: WeekViewHourSegment,
    mouseDownEvent: MouseEvent,
    segmentElement: HTMLElement
  ) {
    // If drag new event, delete old one
    while (this.events.length > 0) {
      this.events.pop();
    }

    const dragToSelectEvent: CalendarEvent = {
      id: this.events.length,
      title: segment.date.toLocaleString('en-US', { hour: 'numeric', minute: 'numeric', hour12: true }),
      start: segment.date,
      meta: {
        tmpEvent: true,
      },
      actions: [
        {
          label: '<i class="bi bi-trash remove-text"></i>',
          cssClass: 'btn-delete-event',
          onClick: ({ event }: { event: CalendarEvent }): void => {
            this.events = this.events.filter((iEvent) => iEvent !== event);
            console.log('Event deleted', event);
          },
        }
      ]
    };
    this.events = [...this.events, dragToSelectEvent];
    const segmentPosition = segmentElement.getBoundingClientRect();
    this.dragToCreateActive = true;
    const endOfView = endOfWeek(this.viewDate, {
      weekStartsOn: this.weekStartsOn,
    });

    fromEvent(document, 'mousemove')
      .pipe(
        finalize(() => {
          delete dragToSelectEvent.meta.tmpEvent;
          this.dragToCreateActive = false;
          if (dragToSelectEvent.end != undefined) {
            dragToSelectEvent.title = dragToSelectEvent.title + " to " + dragToSelectEvent.end.toLocaleString('en-US', { hour: 'numeric', minute: 'numeric', hour12: true });
          }
          
          this.refresh();
        }),
        takeUntil(fromEvent(document, 'mouseup'))
      )
      .subscribe((mouseMoveEvent: Event) => {
        const mouseDownEventDerived = mouseMoveEvent as MouseEvent;
        const minutesDiff = ceilToNearest(
          mouseDownEventDerived.clientY - segmentPosition.top,
          30
        );

        const daysDiff =
          floorToNearest(
            mouseDownEventDerived.clientX - segmentPosition.left,
            segmentPosition.width
          ) / segmentPosition.width;

        const newEnd = addDays(addMinutes(segment.date, minutesDiff), daysDiff);
        if (newEnd > segment.date && newEnd < endOfView) {
          dragToSelectEvent.end = newEnd;
        }
        this.refresh();
      });
  }

  initiateChat() {
    alert(this.events[0].title);
  }

  private refresh() {
    this.events = [...this.events];
    this.cdr.detectChanges();
  }

}
