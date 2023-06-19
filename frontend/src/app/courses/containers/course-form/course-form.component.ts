import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { CoursesService } from '../../services/courses.service';
import { Course } from '../../model/course';

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
    private location: Location,
    private route: ActivatedRoute) {
    this.form = this.formBuilder.group({
      _id: [''],
      name: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
      category: ['', [Validators.required]]
    });

    const course: Course = this.route.snapshot.data["course"];
    this.form.setValue({
      _id: course._id,
      name: course.name,
      category: course.category
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

  getErrorMessage(fieldName: string) {
    const field = this.form.get(fieldName);
    if (field?.hasError('required')) {
      return 'You must enter a valid value';
    }

    if (field?.hasError('minlength')) {
      return 'The minimum length is 5 characters';
    }

    if (field?.hasError('maxlength')) {
      return 'The maximum length is 100 characters';
    }
    return 'error';
  }

  private onSuccess() {
    this._snackBar.open("Course saved", "", { duration: 2000 });
    this.onCancel();
  }

  private onError() {
    this._snackBar.open("Error saving course", "", { duration: 2000 });
  }
}
