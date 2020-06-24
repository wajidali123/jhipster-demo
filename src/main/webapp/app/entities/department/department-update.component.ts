import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDepartment, Department } from 'app/shared/model/department.model';
import { DepartmentService } from './department.service';

@Component({
  selector: 'jhi-department-update',
  templateUrl: './department-update.component.html'
})
export class DepartmentUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    deptName: [null, [Validators.required]],
    deptLoc: [],
    deptContact: [],
    university: [null, [Validators.required]]
  });

  constructor(protected departmentService: DepartmentService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ department }) => {
      this.updateForm(department);
    });
  }

  updateForm(department: IDepartment): void {
    this.editForm.patchValue({
      id: department.id,
      deptName: department.deptName,
      deptLoc: department.deptLoc,
      deptContact: department.deptContact,
      university: department.university
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const department = this.createFromForm();
    if (department.id !== undefined) {
      this.subscribeToSaveResponse(this.departmentService.update(department));
    } else {
      this.subscribeToSaveResponse(this.departmentService.create(department));
    }
  }

  private createFromForm(): IDepartment {
    return {
      ...new Department(),
      id: this.editForm.get(['id'])!.value,
      deptName: this.editForm.get(['deptName'])!.value,
      deptLoc: this.editForm.get(['deptLoc'])!.value,
      deptContact: this.editForm.get(['deptContact'])!.value,
      university: this.editForm.get(['university'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDepartment>>): void {
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
