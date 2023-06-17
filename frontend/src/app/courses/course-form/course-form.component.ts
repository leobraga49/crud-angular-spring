import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { CoursesService } from '../services/courses.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent {

  form: FormGroup;

  constructor(private formBuilder: FormBuilder,
    private coursesService: CoursesService,
    private _snackBar: MatSnackBar) {
    this.form = this.formBuilder.group({
      name: [null],
      category: [null],
    });
  }

  onSubmit() {
    this.coursesService.save(this.form.value).subscribe(
      result => console.log(result),
      error => this.onError());
  }

  onCancel() {
    console.log("onCancel");

  }

  private onError() {
    this._snackBar.open("Error saving course", "", { duration: 2000 });
  }
}
