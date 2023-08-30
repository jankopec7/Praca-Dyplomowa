import { Component, OnInit } from '@angular/core';
import { ProductService } from './model/product.service';
import { Product } from './model/product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

products: Product[] = [];

  constructor(private productService : ProductService) { }

  ngOnInit():  void{
    this.getProduct();
  }
  
  getProduct(){
    this.products = this.productService.getProducts();
  }
}
