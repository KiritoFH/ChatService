import { Component, ViewChild, inject } from '@angular/core';
import { HomeService } from '../common/homeservice.model';
import { HomeServiceService } from '../common/homeservice.service';
import { Observable, OperatorFunction, Subject, merge } from 'rxjs';
import { debounceTime, distinctUntilChanged, filter, map } from 'rxjs/operators';
import { NgbTypeahead } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  homeServiceService: HomeServiceService = inject(HomeServiceService);
  homeServiceList: string[] = [];
  filteredHomeServiceList: string[] = [];

  searchString: string = "";

  @ViewChild('instance', { static: true }) instance: NgbTypeahead;
	focus$ = new Subject<string>();
	click$ = new Subject<string>();

  search: OperatorFunction<string, readonly string[]> = (text$: Observable<string>) => {
		const debouncedText$ = text$.pipe(debounceTime(200), distinctUntilChanged());
		const clicksWithClosedPopup$ = this.click$.pipe(filter(() => !this.instance.isPopupOpen()));
		const inputFocus$ = this.focus$;

		return merge(debouncedText$, inputFocus$, clicksWithClosedPopup$).pipe(
			map((term) =>
				(term === '' ? this.homeServiceList : this.homeServiceList.filter((v) => v.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10),
			),
		);
	};
  
  constructor(private router: Router) {
    this.homeServiceList = this.homeServiceService.getPopularHomeServicesName();
    this.filteredHomeServiceList = this.homeServiceList;
  }

  onSearch() {
    // alert('hi');
    this.router.navigateByUrl('/search/' + this.searchString);
  }
}
