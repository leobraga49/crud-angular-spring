import { Component } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

import { CoursesService } from '../../services/courses.service';
import { catchError, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from '../../model/course';

@Component({
  selector: "app-courses",
  templateUrl: "./courses.component.html",
  styleUrls: ["./courses.component.scss"]
})
export class CoursesComponent {

  courses$: Observable<Course[]>;
  displayedColumns = ["name", "category", "actions"];

  constructor(
    private coursesService: CoursesService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute) {
    this.courses$ = this.coursesService.list()
      .pipe(
        catchError(err => {
          this.onError("Error loading courses");
          return of([]);
        })
      );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  onAdd() {
    this.router.navigate(["new"], { relativeTo: this.route });
  };

  onEdit(course: Course){
    this.router.navigate(["edit",course._id], { relativeTo: this.route });
  }

}
