export interface IUniversity {
  id?: number;
  uniName?: string;
  uniLoc?: string;
  uniContact?: string;
  city?: string;
}

export class University implements IUniversity {
  constructor(public id?: number, public uniName?: string, public uniLoc?: string, public uniContact?: string, public city?: string) {}
}
