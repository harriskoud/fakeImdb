export class User{
    userId: number
    firstname: string
    lastname: string
    username: string
    password: string
    email: string
    creationDate: Date


    constructor (firstname,lastname,username,password){
        this.firstname = firstname
        this.lastname = lastname
        this.username = username
        this.password = password
    }
}