// All the accounts used to authenticate

interface IAccount {
    uid: string,
    username: string,
    password: string,
}

const ACCOUNTS: IAccount[] = [
    { uid: "41043e26-6c33-4c83-a5e1-b4e2dfd827f1", username: "ThLeiNco", password: "password" },
    { uid: "ef9bca7c-40f3-4b5e-bc79-9578801e11d0", username: "AStopHOw", password: "temp" },
    { uid: "a03ce5e6-8387-45c2-9808-c10d810bbd06", username: "IOuSTicE", password: "admin" },
    { uid: "cac6b0d1-8a3f-4239-8aa1-b756a93ddb76", username: "HerYphIc", password: "123321" },
    { uid: "380053a4-65f1-4ca7-b6d0-08c1dfdb5975", username: "gHgoGYNT", password: "qwerty" },
];

export function checkCredential(credential: { username: string, password: string }): { uuid: string, username: string } | null {
    let accountIdx: number = -1;
    ACCOUNTS.forEach((currentAccount: IAccount, index: number) => {
        // When the index has already been found 
        if (accountIdx != -1) {
            return;
        }
        // Check for credential
        if (currentAccount.username == credential.username && currentAccount.password == credential.password) {
            accountIdx = index;
            return;
        }
    });

    return (accountIdx != -1) ? { uuid: ACCOUNTS[accountIdx].uid, username: ACCOUNTS[accountIdx].username } : null;
}