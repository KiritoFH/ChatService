import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-user-message',
  templateUrl: './user-message.component.html',
  styleUrls: ['./chat.component.css']
})
export class UserMessageComponent {
  @Input() time: string;
  @Input() messageOutput: string;
}
