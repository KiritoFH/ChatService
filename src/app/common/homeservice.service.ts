import { Injectable } from "@angular/core";
import { HomeService } from "./homeservice.model";

@Injectable({
    providedIn: 'root'
})
export class HomeServiceService {
    readonly baseUrl = '';

    // Currently mock data
    homeServicesList: HomeService[] = [
        {
            id: 1,
            name: "Cleaning",
            parentServiceId: null
        },
        {
            id: 2,
            name: "Home Repairs",
            parentServiceId: null
        },
        {
            id: 3,
            name: "Furniture Assembly",
            parentServiceId: null
        },
        {
            id: 4,
            name: "Home Moving",
            parentServiceId: null
        },
        {
            id: 5,
            name: "Gardening",
            parentServiceId: null
        },
        {
            id: 6,
            name: "Electrical Help",
            parentServiceId: null
        },
        {
            id: 7,
            name: "Plumbing",
            parentServiceId: null
        },
        {
            id: 8,
            name: "Wall Mounting",
            parentServiceId: null
        },
        {
            id: 9,
            name: "Toilet Cleaning",
            parentServiceId: 1
        },
        {
            id: 10,
            name: "Wifi Installation",
            parentServiceId: 6
        }
    ];

    getAllHomeServices(): HomeService[] {
        return this.homeServicesList;
    }

    getPopularHomeServices(): HomeService[] {
        return this.homeServicesList.filter(service => service.parentServiceId == null);
    }

    getPopularHomeServicesName(): string[] {
        return this.homeServicesList.filter(service => service.parentServiceId == null).map(service => service.name);
    }
}