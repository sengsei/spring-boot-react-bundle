
export interface Todo {
    id: string
    title: string
    text: string
    state: State
}

export enum State {
    Open = 'Open',
    Done = 'Done'
}

