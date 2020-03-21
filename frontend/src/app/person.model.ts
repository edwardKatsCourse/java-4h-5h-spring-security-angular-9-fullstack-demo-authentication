export default class Person {
  private _id: string;
  private _username: string;
  private _password: string;


  constructor(id: string, username: string, password: string) {
    this._id = id;
    this._username = username;
    this._password = password;
  }


  get id(): string {
    return this._id;
  }

  set id(value: string) {
    this._id = value;
  }

  get username(): string {
    return this._username;
  }

  set username(value: string) {
    this._username = value;
  }

  get password(): string {
    return this._password;
  }

  set password(value: string) {
    this._password = value;
  }
}
