import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterDemoAppSharedModule } from 'app/shared/shared.module';
import { UniversityComponent } from './university.component';
import { UniversityDetailComponent } from './university-detail.component';
import { UniversityUpdateComponent } from './university-update.component';
import { UniversityDeleteDialogComponent } from './university-delete-dialog.component';
import { universityRoute } from './university.route';

@NgModule({
  imports: [JhipsterDemoAppSharedModule, RouterModule.forChild(universityRoute)],
  declarations: [UniversityComponent, UniversityDetailComponent, UniversityUpdateComponent, UniversityDeleteDialogComponent],
  entryComponents: [UniversityDeleteDialogComponent]
})
export class JhipsterDemoAppUniversityModule {}
