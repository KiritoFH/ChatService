import { Component, Input } from '@angular/core';
import { ProfileCard } from 'src/app/common/profile-card.model';

@Component({
  selector: 'app-profile-card',
  templateUrl: './profile-card.component.html',
  styleUrls: ['./profile-card.component.css']
})
export class ProfileCardComponent {
  @Input() profile: ProfileCard;
}
