import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProfileCard } from 'src/app/common/profile-card.model';
import { ProfileService } from 'src/app/common/profile.service';

@Component({
  selector: 'app-search-listing',
  templateUrl: './search-listing.component.html',
  styleUrls: ['./search-listing.component.css']
})
export class SearchListingComponent {
  profileService: ProfileService = inject(ProfileService);
  searchString: string | null;
  profiles: ProfileCard[] = [];

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.searchString = this.route.snapshot.paramMap.get('searchString');
    this.profiles = this.profileService.findHandymenByService(this.searchString);
  }
}
