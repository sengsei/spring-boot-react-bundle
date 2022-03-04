import i18n from "i18next"

export default function LanguageSelection (){
    const changeLanguage = (lng: string) => {
        i18n.changeLanguage(lng)
    }
    return (
        <>
            <div>
                <button onClick={() => changeLanguage('de')}>de</button>
                <button onClick={() => changeLanguage('en')}>en</button>
            </div>

        </>
    );
}
