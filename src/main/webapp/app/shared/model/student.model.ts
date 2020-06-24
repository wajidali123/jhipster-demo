import { Moment } from 'moment';

export interface IStudent {
  id?: number;
  fname?: string;
  mname?: string;
  lname?: string;
  email?: string;
  dob?: Moment;
  dept?: string;
  university?: string;
}

export class Student implements IStudent {
  constructor(
    public id?: number,
    public fname?: string,
    public mname?: string,
    public lname?: string,
    public email?: string,
    public dob?: Moment,
    public dept?: string,
    public university?: string
  ) {}
}
