import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AboutComponent } from './about/about.component';
import { SearchComponent } from './search/search.component';
import { ChatComponent } from './chat/chat.component';
import { ProfileComponent } from './profile/profile.component';
import { MenuComponent } from './menu/menu.component';
import { CalendarMonthComponent } from './calendar/calendar-month/calendar-month.component';
import { CalendarWeekComponent } from './calendar/calendar-week/calendar-week.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SearchListingComponent } from './search/search-listing/search-listing.component';
import { ProfileCardComponent } from './search/search-listing/profile-card/profile-card.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    ChatComponent,
    ProfileComponent,
    MenuComponent,
    CalendarMonthComponent,
    CalendarWeekComponent,
    LoginComponent,
    RegisterComponent,
    SearchListingComponent,
    ProfileCardComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    CommonModule,
    CalendarModule.forRoot({ provide: DateAdapter, useFactory: adapterFactory })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
