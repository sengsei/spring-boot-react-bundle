import {render, screen, waitFor} from "@testing-library/react";
import {MemoryRouter} from "react-router";
import TodoList from "./TodoList";



test('that items are added', async () => {
    jest.spyOn(global, 'fetch').mockImplementation(() => {
        return Promise.resolve({
            ok: true,
            json: () => Promise.resolve([{
                title: "Java",
                text: "Unit 1",
            }])
        } as Response)
    })

    render(<TodoList/>, {wrapper: MemoryRouter})

    await waitFor(() => {
        expect(screen.getAllByTestId('add-task').length).toBe(1)
    })
})