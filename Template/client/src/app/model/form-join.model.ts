export interface FormJoin {
  name: string;
  firstName: string;
  email: string;
  password: string;
  confirmPassword: string;
  phone: string;
  address: string;
  city: string;
  state: string;
  country: string;
  birthDate: Date,
  baptismDate?: Date,
  kids: Kids[];
  hasBeenBaptized: boolean;
  department: string | null;
  testimony?: string;
  nationality?: string;
  explanations?: string;
  job?: string;
  currentJob?: string;
  birthLocation: string;
}

export interface Kids {
  firstName: string;
  birthDate: Date;
  familySituation: string;
  profession?: string;
}
