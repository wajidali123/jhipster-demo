export interface IDepartment {
  id?: number;
  deptName?: string;
  deptLoc?: string;
  deptContact?: string;
  university?: string;
}

export class Department implements IDepartment {
  constructor(
    public id?: number,
    public deptName?: string,
    public deptLoc?: string,
    public deptContact?: string,
    public university?: string
  ) {}
}
