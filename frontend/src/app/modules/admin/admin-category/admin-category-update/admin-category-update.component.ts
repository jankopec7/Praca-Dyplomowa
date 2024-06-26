import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminCategoryService } from '../admin-category.service';
import { AdminMessageService } from '../../admin-message.service';
import { AdminCategory } from '../model/adminCategory';

@Component({
  selector: 'app-admin-category-update',
  templateUrl: './admin-category-update.component.html',
  styleUrls: ['./admin-category-update.component.scss']
})

export class AdminCategoryUpdateComponent implements OnInit{

  categoryForm!: FormGroup;

  constructor(private formBuilder: FormBuilder,
    private adminCategoryService: AdminCategoryService,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar,
    private adminMessageService: AdminMessageService
    ){}


  ngOnInit(): void {
    this.categoryForm = this.formBuilder.group({
      name:["", [Validators.required, Validators.minLength(4)]],
      description:[""],
      slug:["", [Validators.required, Validators.minLength(4)]]

    });
    this.getCategory();
  }

  getCategory(){
    this.adminCategoryService.getCategory(Number(this.route.snapshot.params['id']))
    .subscribe(category => this.mapToFormValues(category));
    }

  submit(){
    this.adminCategoryService.saveProduct(Number(this.route.snapshot.params['id']), this.categoryForm.value) 
    .subscribe({
      next: category => {
        this.mapToFormValues(category);
        this.snackBar.open("Category was successfully saved", "", {duration: 3000});
        this.router.navigate(["/admin/categories"]);
      },
      error: err =>{
        this.adminMessageService.addSpringErrors(err.error);
      }
    })
  }
  private mapToFormValues(category:AdminCategory){
    this.categoryForm.setValue({
      name: category.name,
      description: category.description,
      slug: category.slug
    });
  }
}
