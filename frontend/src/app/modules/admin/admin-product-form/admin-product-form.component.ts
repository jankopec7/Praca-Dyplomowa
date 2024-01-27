import { Component, Input, OnInit } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { AdminCategoryNamesDto } from "../common/dto/AdminCategoryNamesDto";
import { FormCategoryService } from "./form-category.service";

@Component({
    selector: 'app-admin-product-form',
    template: `
    <div [formGroup]="parentForm" fxLayout="column">
        <mat-form-field appearance="fill">
            <mat-label>Name</mat-label>
            <input matInput placeholder="Product name" formControlName="name">
            <div *ngIf="name?.invalid && (name?.dirty || name?.touched)" class="erroMessages">
                <div *ngIf="name?.errors?.['required']">
                    Product name is required
                </div>
                <div *ngIf="name?.errors?.['minlength']">
                    Name must have at least 4 letters
                </div>
            </div>
        </mat-form-field>

        <mat-form-field appearance="fill">
            <mat-label>Url</mat-label>
            <input matInput placeholder="Add url" formControlName="slug">
            <div *ngIf="slug?.invalid && (slug?.dirty || slug?.touched)" class="erroMessages">
                <div *ngIf="slug?.errors?.['required']">
                    Name is required
                </div>
                <div *ngIf="slug?.errors?.['minlength']">
                    Name must have at least 4 letters
                </div>
            </div>
        </mat-form-field>

        <mat-form-field appearance="fill">
            <mat-label>description</mat-label>
            <textarea matInput rows="20" placeholder="Add product description" formControlName="description"></textarea>
            <div *ngIf="description?.invalid && (description?.dirty || description?.touched)" class="erroMessages">
                <div *ngIf="description?.errors?.['required']">
                    Description is required
                </div>
                <div *ngIf="description?.errors?.['minlength']">
                    Description must have at least 4 letters
                </div>
            </div>
        </mat-form-field>

        <mat-form-field appearance="fill">
            <mat-label>Full description</mat-label>
            <textarea matInput rows="20" placeholder="Add full description of the product" formControlName="fullDescription"></textarea>
        </mat-form-field>
        <mat-form-field appearance="fill">
            <mat-label>Category</mat-label>
            <mat-select formControlName= "categoryId">
                <mat-option *ngFor="let el of categories" [value]="el.id">
                {{el.name}}
                </mat-option>
            </mat-select>
            <div *ngIf="categoryId?.invalid && (categoryId?.dirty || categoryId?.touched)" class="erroMessages">
                <div *ngIf="categoryId?.errors?.['required']">
                    Category is required
                </div>
            </div>
        </mat-form-field>

        <mat-form-field appearance="fill">
            <mat-label>Price</mat-label>
            <input matInput placeholder="Add product price" formControlName="price">
            <div *ngIf="price?.invalid && (price?.dirty || price?.touched)" class="erroMessages">
                <div *ngIf="price?.errors?.['required']">
                    Price is required
                </div>
                <div *ngIf="price?.errors?.['min']">
                    Price must be > 0
                </div>
            </div>
        </mat-form-field>

        <mat-form-field appearance="fill">
            <mat-label>Bargain price</mat-label>
            <input matInput placeholder="Add product bargain price" formControlName="salePrice">
            <div *ngIf="salePrice?.invalid && (salePrice?.dirty || salePrice?.touched)" class="erroMessages">
                <div *ngIf="salePrice?.errors?.['min']">
                    Bargain price must be > 0
                </div>
            </div>
        </mat-form-field>

        <mat-form-field appearance="fill">
            <mat-label>Currency</mat-label>
            <input matInput placeholder="Add currency" formControlName="currency">
            <div *ngIf="currency?.invalid && (currency?.dirty || currency?.touched)" class="erroMessages">
                <div *ngIf="currency?.errors?.['required']">
                    Currency is required
                </div>
            </div>
        </mat-form-field>

        <div fxLayoutAlign="end">
            <button mat-flat-button color="primary" [disabled]="!parentForm.valid">Save</button>
        </div>
</div>`,
    styles: [`
    .erroMessages{
        color:red;
    }`]
})
export class AdminProductFormConponent implements OnInit {

    @Input() parentForm!: FormGroup;
    categories: Array<AdminCategoryNamesDto> = [];

    constructor(private FormCategoryService: FormCategoryService){

    }

    ngOnInit(): void {

        this.getCategories();

    }

    get name(){
        return this.parentForm.get("name");
    }

    get description(){
        return this.parentForm.get("description");
    }
    get fullDescription(){
        return this.parentForm.get("fullDescription");
    }
    
    get categoryId(){
        return this.parentForm.get("categoryId");
    }

    getCategories(){
        this.FormCategoryService.getCategories()
        .subscribe(categories => this.categories = categories);
    }

    get price(){
        return this.parentForm.get("price");
    }

    get salePrice(){
        return this.parentForm.get("salePrice");
    }

    get currency(){
        return this.parentForm.get("currency");
    }

    get slug(){
        return this.parentForm.get("slug");
    }


}