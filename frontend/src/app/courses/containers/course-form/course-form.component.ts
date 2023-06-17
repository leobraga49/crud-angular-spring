import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { FormGroup, NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { CoursesService } from '../../services/courses.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent {

  form: FormGroup;

  constructor(private formBuilder: NonNullableFormBuilder,
    private coursesService: CoursesService,
    private _snackBar: MatSnackBar,
    private location: Location) {
    this.form = this.formBuilder.group({
      name: [''],
      category: [''],
    });
  }

  onSubmit() {
    this.coursesService.save(this.form.value).subscribe(
      result => this.onSuccess(),
      error => this.onError());
  }

  onCancel() {
    this.location.back();
  }

  private onSuccess() {
    this._snackBar.open("Course saved", "", { duration: 2000 });
    this.onCancel();
  }

  private onError() {
    this._snackBar.open("Error saving course", "", { duration: 2000 });
  }
}
