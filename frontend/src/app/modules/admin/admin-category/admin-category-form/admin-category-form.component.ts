import { Component, Input, OnInit } from "@angular/core";
import { FormGroup } from "@angular/forms";

@Component({
    selector: 'app-admin-category-form',
    template: `
    <div [formGroup]="parentForm" fxLayout="column">
        <mat-form-field appearance="fill">
            <mat-label>Name</mat-label>
            <input matInput placeholder="Category name" formControlName="name">
            <div *ngIf="name?.invalid && (name?.dirty || name?.touched)" class="erroMessages">
                <div *ngIf="name?.errors?.['required']">
                    Name is required
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
            <textarea matInput rows="10" placeholder="Add category description" formControlName="description"></textarea>
            <div *ngIf="description?.invalid && (description?.dirty || description?.touched)" class="erroMessages">
                <div *ngIf="description?.errors?.['required']">
                    Description is required
                </div>
                <div *ngIf="description?.errors?.['minlength']">
                    Description must have at least 4 letters
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
export class AdminCategoryFormComponent implements OnInit {

    @Input() parentForm!: FormGroup;

    constructor(){

    }
    ngOnInit(): void {
    }

    get name(){
        return this.parentForm.get("name");
    }

    get description(){
        return this.parentForm.get("description");
    }

    get slug(){
        return this.parentForm.get("slug");
    }


}