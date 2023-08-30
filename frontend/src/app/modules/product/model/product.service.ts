import { Injectable } from '@angular/core';
import { Product } from './product';


@Injectable({
  providedIn: 'root'
})
export class ProductService{

  constructor() { }

  getProducts(): Product[]{
    return [
      {
        name: "Product 1",
        category: "Category 1",
        description: "Klik Klik Klik",
        price: 1999.99,
        currency:"PLN"
      },
      {
        name: "Product 2",
        category: "Category 2",
        description: "Klik Klik Klik",
        price: 2999.99,
        currency:"PLN"
      },
      {
        name: "Product 3",
        category: "Category 3",
        description: "Klik Klik Klik",
        price: 3999.99,
        currency:"PLN"
      }
    ];

  }
}
