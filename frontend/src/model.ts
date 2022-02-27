
export interface TodoElement {
    id: string
    title: string
    text: string
    state: State.Open
}

export enum State {
    Open,
    Done
}

