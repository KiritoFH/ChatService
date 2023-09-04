import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-response-message',
  templateUrl: './response-message.component.html',
  styleUrls: ['./chat.component.css']
})
export class ResponseMessageComponent {
  @Input() time: string;
  @Input() userName: string;
  @Input() response: string;
}
