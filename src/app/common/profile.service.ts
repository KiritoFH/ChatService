import { Injectable } from "@angular/core";
import { ProfileCard } from "./profile-card.model";

@Injectable({
    providedIn: 'root'
})
export class ProfileService {

    // Mocking search results for particular service
    protected profileList: ProfileCard[] = [
        {
            name: "Shane",
            pricePerHour: 3.50,
            averageReviewScore: 2.9,
            caption: "I am very task focused and efficient. Very worth the money also, please hire me, I love to earn money!",
        },
        {
            name: "Kirito",
            pricePerHour: 7.20,
            averageReviewScore: 4.1,
            caption: "I'll make your sword art online home decor dreams come true!",
        },
        {
            name: "Yu Hui",
            pricePerHour: 29.99,
            averageReviewScore: 5.0,
            caption: "Please hire me only if you can afford it... thanks :D",
        }
    ]

    findHandymenByService(service: string | null): ProfileCard[] {
        alert("finding handymen for service: " + service);
        return this.profileList;
    }
}