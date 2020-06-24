import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';

import { IStudent, Student } from 'app/shared/model/student.model';
import { StudentService } from './student.service';

@Component({
  selector: 'jhi-student-update',
  templateUrl: './student-update.component.html'
})
export class StudentUpdateComponent implements OnInit {
  isSaving = false;
  dobDp: any;

  editForm = this.fb.group({
    id: [],
    fname: [null, [Validators.required]],
    mname: [],
    lname: [null, [Validators.required]],
    email: [null, [Validators.required]],
    dob: [],
    dept: [null, [Validators.required]],
    university: [null, [Validators.required]]
  });

  constructor(protected studentService: StudentService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ student }) => {
      this.updateForm(student);
    });
  }

  updateForm(student: IStudent): void {
    this.editForm.patchValue({
      id: student.id,
      fname: student.fname,
      mname: student.mname,
      lname: student.lname,
      email: student.email,
      dob: student.dob,
      dept: student.dept,
      university: student.university
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const student = this.createFromForm();
    if (student.id !== undefined) {
      this.subscribeToSaveResponse(this.studentService.update(student));
    } else {
      this.subscribeToSaveResponse(this.studentService.create(student));
    }
  }

  private createFromForm(): IStudent {
    return {
      ...new Student(),
      id: this.editForm.get(['id'])!.value,
      fname: this.editForm.get(['fname'])!.value,
      mname: this.editForm.get(['mname'])!.value,
      lname: this.editForm.get(['lname'])!.value,
      email: this.editForm.get(['email'])!.value,
      dob: this.editForm.get(['dob'])!.value,
      dept: this.editForm.get(['dept'])!.value,
      university: this.editForm.get(['university'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStudent>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
