import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { StudentService } from 'app/entities/student/student.service';
import { IStudent, Student } from 'app/shared/model/student.model';

describe('Service Tests', () => {
  describe('Student Service', () => {
    let injector: TestBed;
    let service: StudentService;
    let httpMock: HttpTestingController;
    let elemDefault: IStudent;
    let expectedResult: IStudent | IStudent[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(StudentService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Student(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dob: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Student', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dob: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dob: currentDate
          },
          returnedFromService
        );
        service
          .create(new Student())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Student', () => {
        const returnedFromService = Object.assign(
          {
            fname: 'BBBBBB',
            mname: 'BBBBBB',
            lname: 'BBBBBB',
            email: 'BBBBBB',
            dob: currentDate.format(DATE_FORMAT),
            dept: 'BBBBBB',
            university: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dob: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Student', () => {
        const returnedFromService = Object.assign(
          {
            fname: 'BBBBBB',
            mname: 'BBBBBB',
            lname: 'BBBBBB',
            email: 'BBBBBB',
            dob: currentDate.format(DATE_FORMAT),
            dept: 'BBBBBB',
            university: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dob: currentDate
          },
          returnedFromService
        );
        service
          .query()
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Student', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
