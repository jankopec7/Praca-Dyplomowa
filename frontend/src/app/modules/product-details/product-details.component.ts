import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductDetails } from './model/productDetails';
import { ProductDetailsService } from './product-details.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Review } from './model/review';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss']
})
export class ProductDetailsComponent implements OnInit {

  product!: ProductDetails;
  reviewForm!: FormGroup;

  constructor(
    private productDetailsService: ProductDetailsService,
    private router: ActivatedRoute,
    private formBuilder: FormBuilder,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.getProductDetails();
    this.reviewForm = this.formBuilder.group({
      authorName: ['', [Validators.required, Validators.minLength(2)]],
      content:  ['', [Validators.required, Validators.minLength(4)]],
    });
  }

  getProductDetails() {
    let slug = this.router.snapshot.params['slug'];
    this.productDetailsService.getProductDetails(slug)
      .subscribe(product => this.product = product);
  }

  submit() {
    if(this.reviewForm.valid){
      this.productDetailsService.saveProductReview({
        authorName: this.reviewForm.get("authorName")?.value,
        content:  this.reviewForm.get("content")?.value,
        productId: this.product.id
      } as Review).subscribe(review => {
        this.reviewForm.reset();
        this.snackBar.open('Thanks for your review', '', { duration: 3000, panelClass: "snack-bar-bg-color-ok" });
      });
    }
  }

  get authorName() {
    return this.reviewForm.get('authorName');
  }

  get content() {
    return this.reviewForm.get('content');
  }

}