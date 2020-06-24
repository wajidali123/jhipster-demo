import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'student',
        loadChildren: () => import('./student/student.module').then(m => m.JhipsterDemoAppStudentModule)
      },
      {
        path: 'department',
        loadChildren: () => import('./department/department.module').then(m => m.JhipsterDemoAppDepartmentModule)
      },
      {
        path: 'university',
        loadChildren: () => import('./university/university.module').then(m => m.JhipsterDemoAppUniversityModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JhipsterDemoAppEntityModule {}
